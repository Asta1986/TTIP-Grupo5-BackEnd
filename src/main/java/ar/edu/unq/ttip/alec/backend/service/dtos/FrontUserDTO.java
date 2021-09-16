package ar.edu.unq.ttip.alec.backend.service.dtos;

import ar.edu.unq.ttip.alec.backend.model.FrontUser;
import ar.edu.unq.ttip.alec.backend.model.Province;
import lombok.Getter;

@Getter
public class FrontUserDTO {

    private String username;
    private String name;
    private Boolean active;
    private Province province;
    private Boolean isResponsableInscripto;

    public FrontUserDTO(String username, String name, Province aProvince, boolean isResponsableInscripto, Boolean active){
        this.username=username;
        this.name=name;
        this.active=active;
        this.province=aProvince;
        this.isResponsableInscripto=isResponsableInscripto;
    }

    public static FrontUserDTO fromModel(FrontUser userModel){
        return new FrontUserDTO(userModel.getUsername(),userModel.getName(),userModel.getProvince(),userModel.isResponsableInscripto(),userModel.isEnabled());
    }
}
