
package com.conversationmoderator.Models;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("total_time_words")
    @Expose
    private List<List<Integer>> totalTimeWords = null;
    @SerializedName("total_time_pauses")
    @Expose
    private List<List<Integer>> totalTimePauses = null;
    @SerializedName("gap_btw_turns")
    @Expose
    private List<List<Integer>> gapBtwTurns = null;
    @SerializedName("repetitive_word")
    @Expose
    private List<List<Integer>> repetitiveWord = null;
    @SerializedName("total_duration_percentage")
    @Expose
    private List<List<Integer>> totalDurationPercentage = null;
    @SerializedName("total_turns")
    @Expose
    private List<List<Integer>> totalTurns = null;
    @SerializedName("total_unique_words")
    @Expose
    private List<List<Integer>> totalUniqueWords = null;
    @SerializedName("first_half_spoken")
    @Expose
    private List<List<Integer>> firstHalfSpoken = null;
    @SerializedName("last_half_spoken")
    @Expose
    private List<List<Integer>> lastHalfSpoken = null;
    @SerializedName("average_length_word")
    @Expose
    private List<List<Integer>> averageLengthWord = null;
    @SerializedName("pauses_sentences")
    @Expose
    private List<List<Integer>> pausesSentences = null;
    @SerializedName("conversation_duration")
    @Expose
    private List<List<Integer>> conversationDuration = null;
    @SerializedName("final_df")
    @Expose
    private FinalDf finalDf;
    private final static long serialVersionUID = 7425245525014222351L;

    public List<List<Integer>> getTotalTimeWords() {
        return totalTimeWords;
    }

    public void setTotalTimeWords(List<List<Integer>> totalTimeWords) {
        this.totalTimeWords = totalTimeWords;
    }

    public List<List<Integer>> getTotalTimePauses() {
        return totalTimePauses;
    }

    public void setTotalTimePauses(List<List<Integer>> totalTimePauses) {
        this.totalTimePauses = totalTimePauses;
    }

    public List<List<Integer>> getGapBtwTurns() {
        return gapBtwTurns;
    }

    public void setGapBtwTurns(List<List<Integer>> gapBtwTurns) {
        this.gapBtwTurns = gapBtwTurns;
    }

    public List<List<Integer>> getRepetitiveWord() {
        return repetitiveWord;
    }

    public void setRepetitiveWord(List<List<Integer>> repetitiveWord) {
        this.repetitiveWord = repetitiveWord;
    }

    public List<List<Integer>> getTotalDurationPercentage() {
        return totalDurationPercentage;
    }

    public void setTotalDurationPercentage(List<List<Integer>> totalDurationPercentage) {
        this.totalDurationPercentage = totalDurationPercentage;
    }

    public List<List<Integer>> getTotalTurns() {
        return totalTurns;
    }

    public void setTotalTurns(List<List<Integer>> totalTurns) {
        this.totalTurns = totalTurns;
    }

    public List<List<Integer>> getTotalUniqueWords() {
        return totalUniqueWords;
    }

    public void setTotalUniqueWords(List<List<Integer>> totalUniqueWords) {
        this.totalUniqueWords = totalUniqueWords;
    }

    public List<List<Integer>> getFirstHalfSpoken() {
        return firstHalfSpoken;
    }

    public void setFirstHalfSpoken(List<List<Integer>> firstHalfSpoken) {
        this.firstHalfSpoken = firstHalfSpoken;
    }

    public List<List<Integer>> getLastHalfSpoken() {
        return lastHalfSpoken;
    }

    public void setLastHalfSpoken(List<List<Integer>> lastHalfSpoken) {
        this.lastHalfSpoken = lastHalfSpoken;
    }

    public List<List<Integer>> getAverageLengthWord() {
        return averageLengthWord;
    }

    public void setAverageLengthWord(List<List<Integer>> averageLengthWord) {
        this.averageLengthWord = averageLengthWord;
    }

    public List<List<Integer>> getPausesSentences() {
        return pausesSentences;
    }

    public void setPausesSentences(List<List<Integer>> pausesSentences) {
        this.pausesSentences = pausesSentences;
    }

    public List<List<Integer>> getConversationDuration() {
        return conversationDuration;
    }

    public void setConversationDuration(List<List<Integer>> conversationDuration) {
        this.conversationDuration = conversationDuration;
    }

    public FinalDf getFinalDf() {
        return finalDf;
    }

    public void setFinalDf(FinalDf finalDf) {
        this.finalDf = finalDf;
    }

}
