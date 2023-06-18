package com.tech.zootech.customerservice.service;

import com.tech.zootech.customerservice.domain.dto.DownloadFileDTO;

import java.util.List;

public interface ExcelWriter {
    <T> DownloadFileDTO writeToExcel(String fileName, String contentType, List<T> data);
    String generateExcelFileName(Integer depth);
}
