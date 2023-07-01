package com.brigido.senior.dto.save;

import com.brigido.senior.enumeration.VoteEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class SaveVoteDTO {

    @NotNull
    private VoteEnum voteEnum;
    @NotNull
    private UUID scheduleId;
    @NotNull
    private UUID associateId;
}
