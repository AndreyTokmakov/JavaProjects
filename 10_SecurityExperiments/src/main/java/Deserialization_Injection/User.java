package Deserialization_Injection;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class User implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;
}