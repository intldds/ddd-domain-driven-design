package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;


public class BooleanDTO extends RepresentationModel<BooleanDTO> {

    private boolean result;
    private String msg;


    public BooleanDTO(boolean result, String msg){
        this.result=result;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }


    public boolean getResult() {
        return this.result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BooleanDTO)) return false;
        BooleanDTO that = (BooleanDTO) o;
        return result == that.result &&
                msg.equals(that.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, msg);
    }
}
