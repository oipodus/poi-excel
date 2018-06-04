package ru.vlapin.experiments.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@JsonInclude(NON_NULL)
@FieldDefaults(level = PRIVATE)
public class ClientData {

    @Default
    String companyID = "122740385";

    @Default
    String clientID = "494348762";

    @Default
    String contractNumber = "774836906M";

    @Default
    long mailDirect = 643;

    @Default
    long transType = 1;

    @Default
    long directCtg = 1;

    @Default
    long mailType = 47;

    @Default
    long mailCtg = 4;

    long mailRank;

    @Default
    long sendCtg = 1;

    @Default
    String postMark = "";

    long mass;

    @Default
    long payment = 10;

    @Default
    long value = 1099;

    @Default
    long payType = 1;

    @Valid
    @Default
    AddressData sndrAddressData = AddressData.builder()
            .category("Юридическое лицо")
            .name("Посылкина Анна Иванна")
            .phoneNumber("+7 222 121 23 22")
            .inn("1231231233")
            .kpp(null)
            .address(
                    Address.builder()
                            .addressType("Default")
                            .postalCode("140000")
                            .country(643)
                            .region("region1")
                            .area("area1")
                            .place("place1")
                            .street("street1")
                            .house("house1")
                            .corpus("corpus1")
                            .building("building1")
                            .room("room1")
                            .build())
            .build();

    @Valid
    @Default
    AddressData rcpnAddressData = AddressData.builder()
            .category("Юридическое лицо")
            .name("Получалкина Петрина Пертровна")
            .phoneNumber("+7 123 121 23 12")
            .inn("123")
            .kpp("null")
            .address(
                    Address.builder()
                            .addressType("Default")
                            .postalCode("644021")
                            .country(643)
                            .region("region")
                            .area("area")
                            .place("place")
                            .street("street")
                            .house("house")
                            .corpus("corpus")
                            .building("building")
                            .room("room")
                            .build())
            .build();

    Object mpoDeclaration;
    long height;
    long width;
    long length;
}
