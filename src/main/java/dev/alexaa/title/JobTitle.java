package dev.alexaa.title;

import java.util.Collection;

import static java.util.List.of;

public enum JobTitle {
	ACCOUNTANT("Accountant", of("Accountant", "Bookkeeper", "Auditor", "Clerk")),
	SOFTWARE_ENGINEER("Software Engineer",
		of("Engineer", "Developer", "Software Developer", "Software Engineer")),
	QUANTITY_SURVEYOR("Quantity Surveyor",
		of("Quantity Surveyor", "Chartered Quantity Surveyor", "Estimator", "Chartered Surveyor", "Surveyor")),
	ARCHITECT("Architect", of("Architect", "Landscaper", "Planner"));

	private final String normalizedTitle;
	private final Collection<String> possibleSubjects;
	Title title;

	JobTitle(String normalizedTitle, Collection<String> possibleSubjects) {
		this.normalizedTitle = normalizedTitle;
		this.possibleSubjects = possibleSubjects;
	}

	JobTitle withTitle(Title title) {
		this.title = title;

		return this;
	}

	@Override
	public String toString() {
		return this.normalizedTitle;
	}

	public int sort(JobTitle jobTitle) {
		return Math.round(jobTitle.score() - score());
	}

	private float score() {
		float score = 0F;

		if (title.emptyWithoutFluff() && equals(title.classification))
			return 1F;

		return score;
	}

	private boolean equals(String object) {
		return possibleSubjects
			.stream()
			.anyMatch(object::equals);
	}
}