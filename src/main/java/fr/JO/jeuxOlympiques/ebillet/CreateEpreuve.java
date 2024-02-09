package fr.JO.jeuxOlympiques.ebillet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEpreuve {


    private String nom;

    private Date dateTime;

}
