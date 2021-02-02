function deltas = calculate_deltas(layers, activation_d, h, V, expected_z, w_network, neurons_per_layer)
	deltas = {};
	deltas{layers} = activation_d(V{layers}) .* (expected_z - V{layers});
	last_delta = deltas{layers};
	for j = (layers-1):-1:1
		deltas{j} = activation_d(V{j}(2: (neurons_per_layer(j+1)+1) )) .* (w_network{j+1}(:,2:(neurons_per_layer(j+1)+1))' * last_delta);
		last_delta = deltas{j};
	end
end