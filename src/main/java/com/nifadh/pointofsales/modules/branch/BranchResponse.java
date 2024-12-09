package com.nifadh.pointofsales.modules.branch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class BranchResponse {
    private final Integer id;
    private final String name;
    private final Integer managerUserId;
}
