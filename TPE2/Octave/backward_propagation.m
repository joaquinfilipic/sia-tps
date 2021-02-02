function [w_network, last_w_variation] = backward_propagation(input_vector, w_network, neurons_per_layer, eta, deltas, layers, V, last_w_variation, momentum_enabled, alpha)
	
	V_previous = input_vector;
	w_variation = eta * deltas{1} * V_previous';
	w_network{1} += w_variation + (momentum_enabled * alpha * last_w_variation{1});
	last_w_variation{1} = w_variation;
	for k = 2:layers
		V_previous = V{k-1};
		w_variation = eta * deltas{k} * V_previous';
		w_network{k} += w_variation + (momentum_enabled * alpha * last_w_variation{k});
		last_w_variation{k} = w_variation;
	end

end