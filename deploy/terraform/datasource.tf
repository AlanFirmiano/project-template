data "aws_ami" "apptemplate_server_ami" {
  most_recent = true
  owners      = ["099720109477"] # proprietario da imagem AMI - ubuntu 22.04

  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-jammy-22.04-amd64-server-*"]
  }
}