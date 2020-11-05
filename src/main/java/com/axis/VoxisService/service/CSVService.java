package com.axis.VoxisService.service;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVService {

    public List<String[]> get(String path) {
        List list = new ArrayList();
        try(CSVReader reader = new CSVReader(new FileReader(path))) {
            list = reader.readAll();
        } catch (Exception e) {
        }
        return list;
    }

    public void write(String[] record,String path){
        try(CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeNext(record);
        }catch (Exception e){
        }
    }
}
