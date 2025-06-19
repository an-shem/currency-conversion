package ait.cohort5860.currencyconversion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class ConversionResponseForClientDto {
    public String from;
    public String to;
    public String amount;
    public String result;
    public String response_string;
}
