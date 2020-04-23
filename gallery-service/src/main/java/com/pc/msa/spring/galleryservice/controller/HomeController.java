package com.pc.msa.spring.galleryservice.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.pc.msa.spring.galleryservice.bean.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Value("${server.port}")
    private int localPort;

    @RequestMapping("/")
    public String home() {
        // This is useful for debugging
        // When having multiple instance of gallery service running at different ports.
        // We load balance among them, and display which instance received the request.
        return "Hello from Gallery Service running at port: " + localPort;
    }

    @RequestMapping("/{id}")
    //@Hystrix(fallbackHandler = "getDefaultGallery")
    public Gallery getGallery() {
        return getGallery();
    }

    @RequestMapping("/{id}")
   // @Hystrix(fallbackHandler = "getDefaultGallery")
    public Gallery getGallery(@PathVariable final int id) {
        // create gallery object
        Gallery gallery = new Gallery();
        gallery.setId(id);

        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("image-service", false);
        String imageServiceUrl = instanceInfo.getHomePageUrl();

        // get list of available images
        //  List images = restTemplate.getForObject("http://image-service/images/", List.class);
        List images = restTemplate.getForObject(imageServiceUrl, List.class);
        gallery.setImages(images);

        return gallery;
    }

    public Gallery getDefaultGallery(@PathVariable final int id){
        return new Gallery(id, "Try after some time");
    }
    // -------- Admin Area --------
    // This method should only be accessed by users with role of 'admin'
    // We'll add the logic of role based auth later
    @RequestMapping("/admin")
    public String homeAdmin() {
        return "This is the admin area of Gallery service running at port: " + localPort;
    }
}
