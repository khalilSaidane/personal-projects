package saidane.khalil.catalog.connector;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import saidane.khalil.catalog.config.CustomFeignEncoder;

import java.util.Map;


@FeignClient(value = "document", url = "${environment.url.document}", configuration = CustomFeignEncoder.class)
public interface DocumentClient {

    @PostMapping(value = "/s3/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Headers("Content-Type: multipart/form-data")
    void upload(
            @RequestPart("file")
            MultipartFile data,
            @RequestPart("filename")
            String filename,
            @RequestPart("mimetype")
            String mimetype,
            @RequestPart("metadata")
            Map<String, String> metadata
    );

}
