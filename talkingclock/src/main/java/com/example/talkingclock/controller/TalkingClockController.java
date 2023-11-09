package com.example.talkingclock.controller;

import com.example.talkingclock.response.TalkingClockResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TalkingClockController {

    @GetMapping("/talking-clock/{numericTime}")
    public TalkingClockResponse getHumanFriendlyTime(@PathVariable String numericTime) {
        String humanFriendlyTime = convertToHumanFriendlyText(numericTime);
        TalkingClockResponse response = new TalkingClockResponse(humanFriendlyTime);
        return response;
    }

    public static String convertToHumanFriendlyText(String numericTime) {
        String[] parts = numericTime.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        String[] numbers = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen",
                "Twenty" };

        String humanFriendlyTime;
        if (minute == 0) {
            humanFriendlyTime = numbers[hour] + " o'clock";
        } else if (minute == 5) {
            humanFriendlyTime = "Five past " + numbers[hour];
        } else if (minute == 10) {
            humanFriendlyTime = "Ten past " + numbers[hour];
        } else if (minute == 15) {
            humanFriendlyTime = "Quarter past " + numbers[hour];
        } else if (minute == 30) {
            humanFriendlyTime = "Half past " + numbers[hour];
        } else if (minute == 45) {
            humanFriendlyTime = "Quarter to " + numbers[hour + 1];
        } else if (minute == 50) {
            humanFriendlyTime = "Ten to " + numbers[hour + 1];
        } else if (minute == 55) {
            humanFriendlyTime = "Five to " + numbers[hour + 1];
        } else if (minute <= 20) {
            humanFriendlyTime = numbers[minute] + " past " + numbers[hour];
        } else if (minute < 30) {
            humanFriendlyTime = "Twenty " + numbers[minute - 20] + " past " + numbers[hour];
        } else {
            humanFriendlyTime = "Twenty " + numbers[40 - minute] + " to " + numbers[hour + 1];
        }

        return humanFriendlyTime;
    }
}
