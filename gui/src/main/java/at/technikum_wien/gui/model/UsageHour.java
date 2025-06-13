package at.technikum_wien.gui.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class UsageHour {


    private LocalDateTime hour;

    private double communityProduced;
    private double communityUsed;
    private double gridUsed;
}
