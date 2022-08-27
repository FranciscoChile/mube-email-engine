package com.mube.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailTemplate {
    
    private String emailTo;
    private String from;
    private String subject;
    private String content;
    private Map< String, Object > model;
  
   
    
}
