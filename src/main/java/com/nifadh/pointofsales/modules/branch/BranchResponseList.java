package com.nifadh.pointofsales.modules.branch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class BranchResponseList {
    private final List<BranchResponse> branches;
}
