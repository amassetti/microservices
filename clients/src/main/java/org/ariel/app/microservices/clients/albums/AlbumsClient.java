package org.ariel.app.microservices.clients.albums;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        value = "albums",
        url = "https://jsonplaceholder.typicode.com/albums"
)
public interface AlbumsClient {
    @GetMapping
    List<Albums> getAlbums();

}
