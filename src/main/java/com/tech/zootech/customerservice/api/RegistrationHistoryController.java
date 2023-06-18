package com.tech.zootech.customerservice.api;

import com.tech.zootech.customerservice.domain.dto.DownloadFileDTO;
import com.tech.zootech.customerservice.domain.dto.RegistrationHistoryDTO;
import com.tech.zootech.customerservice.domain.dto.RegistrationStatisticsDTO;
import com.tech.zootech.customerservice.service.ExcelWriter;
import com.tech.zootech.customerservice.service.RegistrationHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tech.zootech.customerservice.service.impl.ExcelWriterImpl.DEFAULT_DEPTH;
import static com.tech.zootech.customerservice.service.impl.ExcelWriterImpl.EXCEL_MIME_TYPE;

@RestController
@RequestMapping("/api/registration-history")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationHistoryController {
    RegistrationHistoryService registrationHistoryService;
    ExcelWriter excelWriter;

    @GetMapping("/customer/{id}")
    public ResponseEntity<RegistrationHistoryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(registrationHistoryService.getByCustomerId(id));
    }

    @PostMapping("/download/statistics")
    public ResponseEntity<Resource> generateStatistics() {
        final List<RegistrationStatisticsDTO> data = registrationHistoryService.getStatistics();
        return generateResultExcelFile(excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, data));
    }

    @PostMapping("/download/{reportHistoryId}")
    public ResponseEntity<Resource> downloadHistory(@PathVariable("reportHistoryId") Long id) {
        final List<RegistrationHistoryDTO> data = registrationHistoryService.getById(id);
        return generateResultExcelFile(excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, data));
    }

    private ResponseEntity<Resource> generateResultExcelFile(DownloadFileDTO downloadFileDTO) {
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, downloadFileDTO.getContentDispositionHeader())
                .contentType(MediaType.parseMediaType(downloadFileDTO.getContentType()))
                .body(downloadFileDTO.getResource());
    }
}
