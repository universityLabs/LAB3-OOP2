package com.tech.zootech.customerservice.service.impl;

import com.tech.zootech.customerservice.domain.dto.DownloadFileDTO;
import com.tech.zootech.customerservice.service.ExcelWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelWriterImpl implements ExcelWriter {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final Integer DEFAULT_DEPTH = 2;
    public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";

    @Override
    public <T> DownloadFileDTO writeToExcel(String fileName, String contentType, List<T> data) {
        try (ByteArrayOutputStream fos = new ByteArrayOutputStream();
             XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());
            fillData(data, sheet, fieldNames);
            workbook.write(fos);
            return DownloadFileDTO.create(contentType, fileName,
                    new InputStreamResource(new ByteArrayInputStream(fos.toByteArray())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DownloadFileDTO();
    }

    @Override
    public String generateExcelFileName(Integer depth) {
        if (depth < 0) {
            return "filename.xlsx";
        }
        return Thread.currentThread().getStackTrace()[depth].getMethodName() + ".xlsx";
    }

    private static <T> void fillData(List<T> data, Sheet sheet, List<String> fieldNames) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int rowCount = 0;
        int columnCount = 0;
        Row row = sheet.createRow(rowCount++);
        for (String fieldName : fieldNames) {
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(fieldName);
        }
        Class<?> clazz = data.get(0).getClass();
        for (T t : data) {
            row = sheet.createRow(rowCount++);
            columnCount = 0;
            setFields(fieldNames, columnCount, row, clazz, t);
        }
    }

    private static <T> void setFields(List<String> fieldNames, int columnCount, Row row, Class<?> clazz, T t) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (String fieldName : fieldNames) {
            Cell cell = row.createCell(columnCount);
            Method method;
            try {
                method = clazz.getMethod("get" + capitalize(fieldName));
            } catch (NoSuchMethodException nme) {
                method = clazz.getMethod("get" + fieldName);
            }
            Object value = method.invoke(t, (Object[]) null);
            if (value != null) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Long) {
                    cell.setCellValue((Long) value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                } else if (value instanceof Enum<?>) {
                    cell.setCellValue(((Enum<?>)value).name());
                } else if (value instanceof LocalDateTime) {
                    cell.setCellValue(((LocalDateTime) value).format(DATE_TIME_FORMATTER));
                } else if (value instanceof LocalDate) {
                    cell.setCellValue(((LocalDate) value).format(DATE_FORMATTER));
                } else {
                    cell.setCellValue(value.toString());
                }
            }
            columnCount++;
        }
    }

    private static List<String> getFieldNamesForClass(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    private static String capitalize(String s) {
        if (s.length() == 0)
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
