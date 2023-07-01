package com.brigido.senior.dto.update;

import com.brigido.senior.enumeration.VoteEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class UpdateVoteDTO {
    @NotNull
    private UUID id;

    private VoteEnum voteEnum;
}
