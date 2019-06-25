use std::*;

fn new_chunk() {
//    let block

    let block_amount = 10;

    let bit_amount = ((block_amount as f64).log2()) as u32 + 1;

    println!("{}", bit_amount);

}
