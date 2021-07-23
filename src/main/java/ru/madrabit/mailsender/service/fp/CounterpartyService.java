package ru.madrabit.mailsender.service.fp;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
public class CounterpartyService {
    private final Map<Float, String> ctpTypes = new HashMap();

    public CounterpartyService() {
        ctpTypes.put(0.0F, "Банки СНГ");
        ctpTypes.put(1.0F, "Банки");
        ctpTypes.put(2.0F, "НФО");
        ctpTypes.put(3.0F, "Филиалы банков РФ");
    }
}
