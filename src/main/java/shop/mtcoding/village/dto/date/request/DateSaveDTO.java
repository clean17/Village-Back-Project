package shop.mtcoding.village.dto.date.request;

import lombok.Getter;
import lombok.ToString;
import shop.mtcoding.village.model.date.Dates;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@ToString
public class DateSaveDTO {

    @Column(name = "dayOfWeekName")
    private List<Map<String, String>> dayOfWeekName;

    public Dates toEntity() {
        Dates date = new Dates();
        date.setDayOfWeekName(dayOfWeekName.toString());
        return date;
    }

//    public Dates toEntity() {
//
//        Dates date = new Dates();
//        List<String> dates = new ArrayList<>();
//        String[] dayOfWeeks = {""};
//        for (String dayOfWeekName : dayOfWeeks) {
//            dates.add(dayOfWeekName);
//        }
//        date.setDayOfWeekName(dates);
//        return new Dates(dates, null);
//    }
}


