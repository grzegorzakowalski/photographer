package com.kowal.photographer.repositorys;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class ConfigRepository {
    private final Path path;

    public ConfigRepository(HttpSession session) {
        this.path = Paths.get(session.getServletContext().getRealPath("/WEB-INF/config.txt"));
    }

    public Integer getMaxPerDay(){
        AtomicReference<Integer> result = new AtomicReference<>(1); // default value
        List<String> allLines = getConfigAsList();
        allLines.forEach( line -> {
            if( line.contains("max-per-day:")){
                result.set(Integer.parseInt(line.replaceAll("[\\[\\];a-z:-]", "")));
            }
        });
        return result.get();
    }

    public void setMaxPerDay(Integer max){
        List<String> config = getConfigAsList();
        StringBuilder sb = new StringBuilder();
        for( String line : config){
            if( !line.startsWith("max-per-day:")){
                sb.append(line).append("\n");
            } else {
                sb.append("max-per-day:").append(max).append(";\n");
            }
        }
        try {
            Files.writeString(path, sb.toString());
        } catch (IOException e) {
            log.error("Error writing into configuration file");
            e.printStackTrace();
        }
    }

    public List<String> getConfigAsList(){
        List<String> allLines = new ArrayList<>();
        try {
            allLines = Files.readAllLines(path);
        } catch (IOException e) {
            log.error("Error reading configuration file");
            e.printStackTrace();
        }
        return allLines;
    }

    public String getConfigAsString(){
        return getConfigAsList().toString();
    }
}
