package guru.sfg.beer.order.service.web.model.beer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "beerName"})
public class BeerDto implements Serializable {

    private UUID id;
    private String beerName;
    private String beerStyle;
    @NotNull
    private String upc;
    private BigDecimal price;
    private Integer stockQuantity;
    private Integer version;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime updatedAt;
}
