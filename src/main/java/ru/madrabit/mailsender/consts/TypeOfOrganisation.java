package ru.madrabit.mailsender.consts;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TypeOfOrganisation {

    public static final float SNG = 0.0F;
    public static final float BANKS = 1.0F;
    public static final float NFO = 2.0F;
    public static final float BANKS_BRANCHES = 3.0F;

}
