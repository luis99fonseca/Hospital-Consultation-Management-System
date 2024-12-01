package com.koerber.hospital.hospital_consult_management.cache;

import com.koerber.hospital.hospital_consult_management.dto.SpecialtyPatientDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class SpecialtiesCache {

    private int lastMinPatientCount;
    private List<SpecialtyPatientDTO> cachedSpecialties;

    private final ReentrantLock lock = new ReentrantLock();

    public SpecialtiesCache() {
        this.lastMinPatientCount = -1;
        this.cachedSpecialties = new ArrayList<>();
    }

    public List<SpecialtyPatientDTO> getCachedSpecialties(int actualMinPatientCount) {
        lock.lock();
        try {
            if (actualMinPatientCount == lastMinPatientCount) {
                System.out.println("CACHED");
                return cachedSpecialties;
            }
            System.out.println("NOT CACHED");
            return null;
        } finally {
            lock.unlock();
        }
    }
    public void updateCachedSpecialties(List<SpecialtyPatientDTO> updatedList, int lastMinPatientCount){
        lock.lock();
        try {
            System.out.println("UPDATED");
            this.cachedSpecialties = updatedList;
            this.lastMinPatientCount = lastMinPatientCount;
        } finally {
            lock.unlock();
        }
    }

    public void refreshCache(){
        lock.lock();
        try {
            System.out.println("REFRESH");
            this.lastMinPatientCount = -1;
            this.cachedSpecialties = new ArrayList<>();
        } finally {
            lock.unlock();
        }
    }
}
