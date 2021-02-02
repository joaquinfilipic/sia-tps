function plot_mse_variation(output, perceptron)
	record_size = columns(output.ecm_record);
	refresh();
	plot(1:output.epoch_count_index - 1, output.ecm_record, "linewidth", 2);
	set(gca, "linewidth", 3, "fontsize", 12);
	title ("Mean Squared Error");
	xlabel ("Epochs");
	ylabel ("MSE");
	legend ("MSE variation");
end