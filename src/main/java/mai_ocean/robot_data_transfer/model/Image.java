package mai_ocean.robot_data_transfer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tb_maiocean_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_image")
    private String id;

    @Column(name = "dt_date")
    private LocalDate date;

    @Column(name = "hr_time")
    private LocalTime time;

    @Column(name = "ds_depth")
    @NotNull(message = "The ds_depth column can't be null")
    private Integer depth;

    @Column(name = "ds_latitude")
    @NotNull(message = "The ds_latitude column can't be null")
    private Double latitude;

    @Column(name = "ds_longitude")
    @NotNull(message = "The ds_longitude column can't be  null")
    private Double longitude;

    @NotNull(message = "The bt_imageData column can't be null")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_image_content", nullable = false, referencedColumnName = "id_image_content")
    private ImageContent imageContent;

    @ManyToOne
    @JoinColumn(name = "id_image_fk")
    @JsonIgnoreProperties(value = {"images"})
    private Robot robot;

}
