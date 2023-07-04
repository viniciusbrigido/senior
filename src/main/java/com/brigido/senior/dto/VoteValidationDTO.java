package com.brigido.senior.dto;

import com.brigido.senior.enumeration.VoteStatusEnum;
import lombok.*;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class VoteValidationDTO {
    private VoteStatusEnum status;

    public boolean isAbleToVote() {
        return VoteStatusEnum.ABLE_TO_VOTE.equals(status);
    }
}
