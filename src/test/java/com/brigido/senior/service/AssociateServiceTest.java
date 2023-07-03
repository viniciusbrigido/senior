package com.brigido.senior.service;

import com.brigido.senior.BaseTests;
import com.brigido.senior.dto.response.ResponseAssociateDTO;
import com.brigido.senior.dto.save.SaveAssociateDTO;
import com.brigido.senior.dto.update.UpdateAssociateDTO;
import com.brigido.senior.exception.InvalidCpfException;
import com.brigido.senior.exception.NotFoundEntityException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

@SpringJUnitConfig(BaseTests.TestConfig.class)
@RunWith(SpringRunner.class)
public class AssociateServiceTest extends BaseTests {

    @Autowired
    private AssociateService associateService;

    @Test
    public void saveAssociateTest() {
        ResponseAssociateDTO associate = associateService.save(getSaveAssociateDTO());
        assertThat(associate).isNotNull();
        assertThat(associate.getId()).isNotNull();
    }

    @Test
    public void saveAssociateInvalidCPFTest() {
        assertThatThrownBy(() -> associateService.save(getSaveAssociateDTOInvalidCPF())).isInstanceOf(InvalidCpfException.class);
    }

    @Test
    public void saveAssociateCPFAlreadyRegisteredTest() {
        associateService.save(getSaveAssociateDTO());
        assertThatThrownBy(() -> associateService.save(getSaveAssociateDTO())).isInstanceOf(InvalidCpfException.class);
    }

    @Test
    public void deleteAssociateTest() {
        ResponseAssociateDTO associate = associateService.save(getSaveAssociateDTO());
        assertThatCode(() -> associateService.delete(associate.getId())).doesNotThrowAnyException();
    }

    @Test
    public void findAssociateByIdTest() {
        ResponseAssociateDTO associate = associateService.save(getSaveAssociateDTO());
        assertThatCode(() -> associateService.findById(associate.getId())).doesNotThrowAnyException();
    }

    @Test
    public void findAssociateByIdNotFoundTest() {
        assertThatThrownBy(() -> associateService.findById(UUID.randomUUID())).isInstanceOf(NotFoundEntityException.class);
    }

    @Test
    public void updateAssociateTest() {
        ResponseAssociateDTO associate = associateService.save(getSaveAssociateDTO());
        String newName = "New Associate";
        UpdateAssociateDTO updateAssociateDTO = UpdateAssociateDTO.builder().id(associate.getId()).name(newName).build();
        associateService.update(updateAssociateDTO);

        ResponseAssociateDTO associateUpdate = associateService.findByIdDTO(associate.getId());
        assertThat(associateUpdate).isNotNull();
        assertThat(associateUpdate.getId()).isNotNull();
        assertThat(associateUpdate.getName()).isEqualTo(newName);
    }

    private SaveAssociateDTO getSaveAssociateDTO() {
        return SaveAssociateDTO.builder()
                .name("Associate")
                .cpf("27459443638")
                .build();
    }

    private SaveAssociateDTO getSaveAssociateDTOInvalidCPF() {
        return SaveAssociateDTO.builder()
                .name("Associate")
                .cpf("11111111111")
                .build();
    }
}
