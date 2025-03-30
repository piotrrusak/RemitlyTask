package com.example.task.loader;

import com.example.task.service.BankService;
import com.example.task.service.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CsvImportService csvImportService;

    @Override
    public void run(String... args) throws Exception {
        csvImportService.importFromCsv(); // Bez parametru
    }
}
