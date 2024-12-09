package com.nifadh.pointofsales.modules.branch;

import com.nifadh.pointofsales.modules.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BranchMapper {
    public Branch branchRequestToBranch(BranchRequest branchRequest, User manager){
        return Branch.builder()
                .name(branchRequest.getName())
                .manager(manager)
                .build();
    }

    public BranchResponse branchToBranchResponse(Branch branch) {
        return BranchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .managerUserId(branch.getManager().getId())
                .build();
    }

    public BranchResponseList branchListToBranchResponseList(List<Branch> branches) {
        List<BranchResponse> branchResponseList = new ArrayList<>();

        branches.forEach(branch -> {
            BranchResponse branchResponse = branchToBranchResponse(branch);
            branchResponseList.add(branchResponse);
        });
        return new BranchResponseList(branchResponseList);
    }
}
