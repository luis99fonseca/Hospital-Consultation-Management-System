package com.koerber.hospital.hospital_consult_management.cache;

import com.koerber.hospital.hospital_consult_management.dto.SpecialtyPatientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class SpecialtiesCache {

    private static final Logger logger = LoggerFactory.getLogger(SpecialtiesCache.class);
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
                logger.debug("There is Cached data");
                return cachedSpecialties;
            }
            logger.debug("There is not Cached data");
            return null;
        } finally {
            lock.unlock();
        }
    }
    public void updateCachedSpecialties(List<SpecialtyPatientDTO> updatedList, int lastMinPatientCount){
        lock.lock();
        try {
            this.cachedSpecialties = updatedList;
            this.lastMinPatientCount = lastMinPatientCount;
            logger.debug("Cache Updated");
        } finally {
            lock.unlock();
        }
    }

    public void refreshCache(){
        lock.lock();
        try {
            this.lastMinPatientCount = -1;
            this.cachedSpecialties = new ArrayList<>();
            logger.debug("Cache Refreshed");
        } finally {
            lock.unlock();
        }
    }
}
