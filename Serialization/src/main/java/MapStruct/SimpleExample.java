package MapStruct;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/*
enum CarType
{
    Sedan,
    CUV
}*/

@Value
//@AllArgsConstructor
class Car
{
    String make;
    int numberOfSeats;
    String type;
}

@Value
@NoArgsConstructor
class CarDTO
{
    String make = "";
    int numberOfSeats = 0;
    String type = "";
}

@Mapper
interface CarMapper
{
    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    // @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDTO carToCarDto(Car car);
}

public class SimpleExample
{
    public static void main(String[] args)
    {
        //given
        Car car = new Car( "Morris", 5, "Sedan" );

        //when
        CarDTO carDto = CarMapper.INSTANCE.carToCarDto( car );

        //then
        assertThat( carDto ).isNotNull();
        assertThat( carDto.getMake() ).isEqualTo( "Morris" );
        assertThat( carDto.getNumberOfSeats() ).isEqualTo( 5 );
        assertThat( carDto.getType() ).isEqualTo( "Sedan" );
    }
}
