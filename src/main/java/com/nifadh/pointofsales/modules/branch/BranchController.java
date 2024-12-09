package com.nifadh.pointofsales.modules.branch;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BranchController {
    private final BranchService branchService;

    @PostMapping("/branch")
    public BranchResponse addBranch(@Valid @RequestBody BranchRequest branchRequest) {
        return branchService.addBranch(branchRequest);
    }

    @GetMapping("/branch")
    public BranchResponseList getAllBranches() {
        return branchService.getAllBranches();
    }


}
