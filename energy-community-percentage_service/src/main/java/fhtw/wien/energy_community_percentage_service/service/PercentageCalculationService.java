package fhtw.wien.energy_community_percentage_service.service;

import fhtw.wien.energy_community_percentage_service.model.CurrentPercentage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import fhtw.wien.energy_community_percentage_service.repo.CurrentPercentageRepository;
import fhtw.wien.energy_community_percentage_service.repo.UsageHourRepository;

import java.time.LocalDateTime;

@Service
public class PercentageCalculationService {

    private final UsageHourRepository usageHourRepository;
    private final CurrentPercentageRepository percentageRepository;

    public PercentageCalculationService(
            UsageHourRepository usageHourRepository,
            CurrentPercentageRepository percentageRepository
    ) {
        this.usageHourRepository = usageHourRepository;
        this.percentageRepository = percentageRepository;
    }

    @Scheduled(fixedRate = 15000) // every 15 seconds
    public void calculate() {
        LocalDateTime hour = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);

        usageHourRepository.findById(hour).ifPresent(usage -> {
            double totalUsed = usage.getCommunityUsed() + usage.getGridUsed();
            double gridPortion = totalUsed > 0 ? (usage.getGridUsed() / totalUsed) * 100.0 : 0.0;

            CurrentPercentage percentage = new CurrentPercentage();
            percentage.setHour(hour);
            percentage.setCommunityDepleted(100.0); // in your model always 100
            percentage.setGridPortion(gridPortion);

            percentageRepository.save(percentage);
        });
    }
}

