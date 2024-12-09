package com.nifadh.pointofsales.modules.branch;

import com.nifadh.pointofsales.exception.ResourceNotFoundException;
import com.nifadh.pointofsales.modules.user.User;
import com.nifadh.pointofsales.modules.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;
    private final UserService userService;
    private final BranchMapper branchMapper;

    public BranchResponse addBranch(BranchRequest branchRequest) {
        User manager = userService.findUserById(branchRequest.getManagerId());

        if (!manager.getRole().name().equalsIgnoreCase("Branch_Manager")) {
            throw new ResourceNotFoundException("Given user ID is not a manager!");
        }

        Branch branch = branchMapper.branchRequestToBranch(branchRequest, manager);
        branch = branchRepository.save(branch);
        return branchMapper.branchToBranchResponse(branch);
    }

    public BranchResponseList getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        return branchMapper.branchListToBranchResponseList(branches);
    }

    public List<Branch> getBranchList() {
        return branchRepository.findAll();
    }

    public Branch findBranchById(Integer branchId) {
        checkIfBranchIdIsValid(branchId);
        return branchRepository.findById(branchId).get();
    }

    private void checkIfBranchIdIsValid(Integer branchId) {
        if (!branchRepository.existsById(branchId)) {
            throw new ResourceNotFoundException("Branch ID: " + " does not exist!");
        }
    }


}
