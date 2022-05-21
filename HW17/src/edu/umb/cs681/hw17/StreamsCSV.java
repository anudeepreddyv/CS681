package edu.umb.cs681.hw17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsCSV {

    public static void main(String[] args) {

        Path path = Path.of("Data", "PVIData.csv");

        try( Stream<String> lines = Files.lines(path) ){
            List<List<String>> matrix = lines.map(line -> {
                        return Stream.of( line.split(",") )
                                .map(value->value.substring(0, value.length()))
                                .collect( Collectors.toList() ); })
                    .collect( Collectors.toList() );


            List florida = matrix.stream().filter((i) -> i.get(4).contains("Florida")).
                    collect(Collectors.toList());
            String totalCases = matrix.stream().
                    parallel().filter((i) -> i.get(4).contains("Florida"))
                    .map((i) -> i.get(6)).reduce("0",
                            (temp, result) -> String.valueOf(Integer.parseInt(temp) + Integer.parseInt(result)));

            List taylor = matrix.stream().filter((i) -> i.get(5).contains("Taylor")).
                    collect(Collectors.toList()).get(0);

            List union = matrix.stream().filter((i) -> i.get(5).contains("Union")).
                    collect(Collectors.toList()).get(0);

            float tayIR = Float.parseFloat((String) taylor.get(8));
            float unIR = Float.parseFloat((String) union.get(8));

            System.out.println("\nTotal no. of cases in Florida: " + totalCases);
            System.out.println("\nInfection rate in Taylor County: " + taylor.get(8));
            System.out.println("\nInfection rate in Union County: " + union.get(8));

            if(tayIR > unIR){
                System.out.println("\nInfection Rate in Taylor is "+(tayIR-unIR)+" times more than Union");
            }else{
                System.out.println("\nInfection Rate in Union is "+(unIR-tayIR)+" times more than Taylor");
            }

        }

        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
