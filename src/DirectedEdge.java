public class DirectedEdge {
        private int from;
        private int to;
        private double weight;

        DirectedEdge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() { return this.from; }
        public int getTo() { return this.to; }
        public double getWeight() {  return this.weight; }
}
