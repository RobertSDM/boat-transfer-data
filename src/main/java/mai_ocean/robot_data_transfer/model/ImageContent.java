package mai_ocean.robot_data_transfer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_maiocen_image_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageContent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_image_content")
    private String id;

    @Column(name = "ds_content")
    @NotNull(message = "the ds_content column can't be null")
    @Lob
    private Byte[] content;
}
