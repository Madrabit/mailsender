package ru.madrabit.mailsender.consts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.madrabit.mailsender.model.OrgType;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class TypeOfOrganisation {

    public static final float SNG = 0.0F;
    public static final float BANKS = 1.0F;
    public static final float NFO = 2.0F;
    public static final float BANKS_BRANCHES = 3.0F;

//    private Map<String, OrgType> typeMap = new HashMap<>();

    public TypeOfOrganisation() {
//        typeMap.put("SNG", new OrgType("Банки СНГ", 0.0F, false));
//        typeMap.put("BANKS", new OrgType("Банки", 1.0F, false));
//        typeMap.put("NFO", new OrgType("НФО", 2.0F, true));
//        typeMap.put("BANKS_BRANCHES", new OrgType("Филиалы Банков", 3.0F, false));
    }
}
