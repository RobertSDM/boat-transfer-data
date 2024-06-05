package mai_ocean.robot_data_transfer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tb_maiocean_image")
@Data
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_image")
    private String id;

    @Column(name = "dt_time")
    @NotBlank(message = "The dt_time column can't be empty or null")
    private LocalDateTime time;

    @Column(name = "ds_depth")
    @NotBlank(message = "The ds_depth column can't be empty or null")
    private Integer depth;

    @Column(name = "ds_latitude")
    @NotBlank(message = "The ds_latitude column can't be empty or null")
    private Double latitude;

    @Column(name = "ds_longitude")
    @NotBlank(message = "The ds_longitude column can't be empty or null")
    private Double longitude;

    @NotBlank(message = "The bt_imageData column can't be empty or null")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_image_content", nullable = false, referencedColumnName = "id_image_content")
    private ImageContent imageContent;

    public Image(Date time, Integer depth, Double latitude, Double longitude, ImageContent imageContent) {
        this.time = LocalDateTime.now();
        this.depth = depth;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageContent = imageContent;
    }
}
