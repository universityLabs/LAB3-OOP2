package com.tech.zootech.customerservice.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
@Setter
public class DownloadFileDTO {
    String contentType;
    String contentDisposition;
    Resource resource;

    public static DownloadFileDTO create(String contentType, String contentDisposition, Resource resource) {
        DownloadFileDTO downloadFileDTO = new DownloadFileDTO();
        downloadFileDTO.setContentType(contentType);
        downloadFileDTO.setContentDisposition(contentDisposition);
        downloadFileDTO.setResource(resource);
        return downloadFileDTO;
    }

    public String getContentDispositionHeader() {
        return String.format("attachment; filename=\"%s\"", this.contentDisposition);
    }
}
