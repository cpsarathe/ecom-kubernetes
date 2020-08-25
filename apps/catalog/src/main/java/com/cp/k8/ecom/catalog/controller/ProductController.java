package com.cp.k8.ecom.catalog.controller;

import com.cp.k8.ecom.catalog.domain.Product;
import com.cp.k8.ecom.catalog.dto.ProductDTO;
import com.cp.k8.ecom.catalog.dto.ServiceResponseDTO;
import com.cp.k8.ecom.catalog.repository.ProductRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/product")
@CommonsLog
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/offer-for-me")
    public ProductDTO offerForMe() {
        return ProductDTO.builder().message("We have got a exciting offer in our Perfumes.").build();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponseDTO addProduct(@RequestBody ProductDTO productDTO , HttpServletRequest request) {
        ServiceResponseDTO response = new ServiceResponseDTO();
        log.info("begin add product");
        response.setStatus("success");
        response.setMessage("");
        log.info("add:response:" + response);

        Product product = convertProductDTOToProduct(productDTO);
        product.setId(generateProductId());
        product.setUrl(generateUrl(request,product.getName()));
        product.setCreatedDate(Calendar.getInstance().getTime());
        product.setUpdatedDate(Calendar.getInstance().getTime());

        Product savedProduct = productRepository.save(product);
        response.setBody(savedProduct);

        log.info("finished adding product with name : " + productDTO.getName() + " . Saved Product Id is :" + savedProduct.getId());

        return response;
    }

    private Product convertProductDTOToProduct(ProductDTO productDTO) {
        return Product.builder().name(productDTO.getName())
                .imageUrl(productDTO.getImageUrl())
                .quantity(productDTO.getQuantity())
                .retailPrice(productDTO.getRetailPrice())
                .salePrice(productDTO.getSalePrice())
                .sellerId(productDTO.getSellerId())
                .build();
    }

    private String generateUrl(HttpServletRequest request , String name) {
        String url = "/";
        if (name != null) {
            name = name.trim();
            url = url + name.replaceAll(" ", "-").replaceAll("[^\\w\\s-]", "").toLowerCase();
        }
        if (!url.endsWith("/")) {
            url += "/";
        }
        url += generate10DigitRandomNumber();
        log.debug("Generated url for the product : " + url);
        return url;
    }

    private String generate10DigitRandomNumber() {
        long v = ThreadLocalRandom.current().nextLong(1L, 10000000000L);
        return String.format("%010d", v);
    }

    private String generateProductId() {
        return UUID.randomUUID().toString();
    }
}
