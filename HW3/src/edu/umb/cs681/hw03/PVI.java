package edu.umb.cs681.hw03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PVI {
    public List<List<String>> PVI(){
        try(Stream<String> lines = Files.lines(Path.of("Model_12.4_20210113_results.csv"))){
            List<List<String>> matrix = lines.map(line -> {
                return Stream.of( line.split(",") ).map(value->value.substring(0, value.length()))
                        .collect( Collectors.toList() ); }).collect( Collectors.toList());
            return matrix;
        } catch (IOException ex) {}
        return null;
    }
}