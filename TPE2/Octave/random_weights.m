function [w_network, last_w_variation] = random_weights(layers, neurons_per_layer)
	w_network = {};
	for n = 1:layers
		w_network{n} = unifrnd(-0.5,0.5,neurons_per_layer(n + 1), neurons_per_layer(n) + 1);

		%last_w_variation{n} = zeros(neurons_per_layer(n + 1), neurons_per_layer(n));
		last_w_variation{n} = zeros(neurons_per_layer(n + 1), neurons_per_layer(n) + 1);

	end
end