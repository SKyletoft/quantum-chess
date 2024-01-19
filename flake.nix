{
	inputs = {
		nixpkgs.url     = "github:nixos/nixpkgs/nixos-unstable";
		nixgl.url       = "github:nix-community/nixgl";
		flake-utils.url = "github:numtide/flake-utils";
	};

	outputs = { self, nixpkgs, flake-utils, nixgl }:
		flake-utils.lib.eachDefaultSystem(system:
			let pkgs = import nixpkgs {
					inherit system;
					overlays = [ nixgl.overlay ];
				};
				lib = nixpkgs.lib;

				dbg-env = with pkgs; [
					gnumake
					gdb
					valgrind
				];
				kotlin-env = with pkgs; [
					kotlin
					kotlin-language-server
					ktlint
					glfw
					pkgs.nixgl.nixGLIntel
				];

			in rec {
				devShells.default = pkgs.mkShell {
					nativeBuildInputs = kotlin-env ++ dbg-env;
				};
			}
		);
}
