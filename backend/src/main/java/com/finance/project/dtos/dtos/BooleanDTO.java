package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

/**
 * The type Boolean dto.
 * Extends RepresentationModel in order to be able to add hypermedia links
 */
public class BooleanDTO extends RepresentationModel<BooleanDTO> {

    private boolean result;
    private String msg;

    /**
     * Instantiates a new Boolean dto.
     *
     * @param result the result
     * @param msg    the msg
     */
    public BooleanDTO(boolean result, String msg){
        this.result=result;
        this.msg=msg;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public boolean getResult() {
        return this.result;
    }

    /**
     * Equals boolean used to compare objects of the class BooleanDTO
     *
     * @param o the object o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BooleanDTO)) return false;
        BooleanDTO that = (BooleanDTO) o;
        return result == that.result &&
                msg.equals(that.msg);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(result, msg);
    }
}
