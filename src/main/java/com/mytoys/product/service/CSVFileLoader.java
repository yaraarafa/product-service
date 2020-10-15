package com.mytoys.product.service;

import com.mytoys.product.config.CSVConfig;
import com.mytoys.product.entity.Product;
import com.mytoys.product.exception.DataLoadingException;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CSVFileLoader {

    private CSVConfig csvConfig;

    private final ProductService productService;

    @EventListener(ApplicationReadyEvent.class)
    public void loadFileToDatabase() {
        String completeFileName = csvConfig.getPath() + csvConfig.getFileName();
        log.info("Application started trying to load data from CSV file " + completeFileName + " ...");
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(completeFileName));
            // read the data from the file
            List<Product> products = new CsvToBeanBuilder<Product>(reader).
                    withType(Product.class)
                    .withSkipLines(1)
                    .build()
                    .parse();
            log.info("Successfully loaded the CSV file to the application. trying to save to Database ");
            //save products to database
            productService.saveProducts(products);
            log.info(products.size() + " Products saved successfully ");
            // close the reader
            reader.close();

        } catch (IOException ex) {
            log.error("Something went wrong with reading the CSV file while trying to load it in the database: " + ex.getMessage());
        } catch (RuntimeException ex) {
            //used to catch data mismatch exceptions when converting the csv file to entities
            log.error("Something went wrong with converting data from the CSV file to Product entities : " + ex.getMessage());
        }
    }
}
